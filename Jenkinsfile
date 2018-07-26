//----------------------------------------------------------------------------------------
 //----------------------------------------------------------------------------------------
 //  PARAMETERS
 //----------------------------------------------------------------------------------------
 //  searchString:      String for Smoke Tests to be found on Example Web Page (Kubernetes Demo for Customer)
 //  webappUrl:         url for WebApp to test  (http://192.168.27.199:32333/demo/)
 //  gitlabProjectUrl:  url for GitLab Project   (http://192.168.27.199:30126/demo/libertydemo.git)
 //
 //----------------------------------------------------------------------------------------
 //----------------------------------------------------------------------------------------
node {
 def mvnHome

   //----------------------------------------------------------------------------------------
   //----------------------------------------------------------------------------------------
   //  GET SOURCES FROM GIT
   //----------------------------------------------------------------------------------------
   //----------------------------------------------------------------------------------------
   stage('Checkout') { // for display purposes
     withCredentials([usernamePassword(credentialsId: 'GIT', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
       // Get some code from a GitHub repository
       updateGitlabCommitStatus name: 'Build devops-demo:1.${BUILD_NUMBER}', state: 'pending'
       git credentialsId: '$PASSWORD', url: '${gitlabProjectUrl}'
     }
     // Get the Maven tool.
     // ** NOTE: This 'M3' Maven tool must be configured
     // **       in the global configuration.
     mvnHome = tool 'M3'
   }


   //----------------------------------------------------------------------------------------
   //----------------------------------------------------------------------------------------
   //  MAVEN BUILD
   //----------------------------------------------------------------------------------------
   //----------------------------------------------------------------------------------------
   stage('Build Maven') {

     updateGitlabCommitStatus name: 'Build devops-demo:1.${BUILD_NUMBER}', state: 'running'

     // Adapt Demo Page
     sh "sed -i 's/DevOps Demo/DevOps Demo - 1.${BUILD_NUMBER}/g'  ./src/main/webapp/index.html"
     sh "cat ./src/main/webapp/index.html"

     // Run the maven build
     if (isUnix()) {
       sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore package"
     } else {
       bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore package/)
     }
   }


   //----------------------------------------------------------------------------------------
   //----------------------------------------------------------------------------------------
   //  DOCKER BUILD
   //----------------------------------------------------------------------------------------
   //----------------------------------------------------------------------------------------
   stage('Build Docker') {
     withCredentials([usernamePassword(credentialsId: 'ICP', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
       sh "cd '$WORKSPACE'"
       sh "docker build -t devops-demo:1.${BUILD_NUMBER} ."
       sh "docker login mycluster.icp:8500 -u $USERNAME -p $PASSWORD"
       sh "docker tag devops-demo:1.${BUILD_NUMBER} mycluster.icp:8500/default/devops-demo:1.${BUILD_NUMBER}"
       sh "docker push mycluster.icp:8500/default/devops-demo:1.${BUILD_NUMBER}"
     }
   }


   //----------------------------------------------------------------------------------------
   //----------------------------------------------------------------------------------------
   //  KUBERNETES DEPLOY FILE
   //----------------------------------------------------------------------------------------
   //----------------------------------------------------------------------------------------
   stage('Build Kubernetes') {
     sh "sed -i 's/devops-demo:99.99/devops-demo:1.${BUILD_NUMBER}/g' deploy.yaml"
     sh "cat deploy.yaml"
   }


   //----------------------------------------------------------------------------------------
   //----------------------------------------------------------------------------------------
   //  KUBERNETES DEPLOY
   //----------------------------------------------------------------------------------------
   //----------------------------------------------------------------------------------------
   stage('Deploy DEV') {
     sh "kubectl apply -f deploy.yaml"
   }


   //----------------------------------------------------------------------------------------
   //----------------------------------------------------------------------------------------
   //  SMOKE TEST DEPLOYMENT
   //----------------------------------------------------------------------------------------
   //----------------------------------------------------------------------------------------
   stage('Smoke Test') {
     sh ''' sleep 10
            content=$(wget ${webappUrl} -q -O -)
            #echo $content


            substring="${searchString}"

            if test "${content#*$substring}" != "$content"
            then
             return 0    # $substring is in $content
            else
             return 1    # $substring is not in $content
            fi
       '''

  }


   //----------------------------------------------------------------------------------------
   //----------------------------------------------------------------------------------------
   //  PUSH VERSION TO UCD
   //----------------------------------------------------------------------------------------
   //----------------------------------------------------------------------------------------
   stage('UrbanCode Import') {
     step([$class: 'UCDeployPublisher',
       siteName: 'UCDS',
       component: [
         $class: 'com.urbancode.jenkins.plugins.ucdeploy.VersionHelper$VersionBlock',
         componentName: 'DEMO',
         delivery: [
           $class: 'com.urbancode.jenkins.plugins.ucdeploy.DeliveryHelper$Push',
           pushVersion: '${BUILD_NUMBER}',
           baseDir: '/tmp/jenkins/workspace/demo/',
           fileIncludePatterns: '*.*',
           fileExcludePatterns: '',
           //pushProperties: 'jenkins.server=Local\njenkins.reviewed=false',
           pushDescription: 'Pushed from Jenkins'
         ]
       ]
     ])
   }


   //----------------------------------------------------------------------------------------
   //----------------------------------------------------------------------------------------
   //  DEPLOY VERSION WITH UCD
   //----------------------------------------------------------------------------------------
   //----------------------------------------------------------------------------------------
   stage('UrbanCode Deploy TEST') {
     step([$class: 'UCDeployPublisher',
       siteName: 'UCDS',
       deploy: [
         $class: 'com.urbancode.jenkins.plugins.ucdeploy.DeployHelper$DeployBlock',
         deployApp: 'DEMO',
         deployEnv: 'TEST',
         deployProc: 'deploy',
         deployVersions: 'DEMO:${BUILD_NUMBER}',
         deployOnlyChanged: false
       ]
     ])
   }


   stage('UrbanCode Deploy INT') {
     step([$class: 'UCDeployPublisher',
       siteName: 'UCDS',
       deploy: [
         $class: 'com.urbancode.jenkins.plugins.ucdeploy.DeployHelper$DeployBlock',
         deployApp: 'DEMO',
         deployEnv: 'INT',
         deployProc: 'deploy',
         deployVersions: 'DEMO:${BUILD_NUMBER}',
         deployOnlyChanged: false
       ]
     ])

     updateGitlabCommitStatus name: 'Build devops-demo:1.${BUILD_NUMBER}', state: 'success'

   }


   stage('UrbanCode Deploy PROD') {
     step([$class: 'UCDeployPublisher',
       siteName: 'UCDS',
       deploy: [
         $class: 'com.urbancode.jenkins.plugins.ucdeploy.DeployHelper$DeployBlock',
         deployApp: 'DEMO',
         deployEnv: 'PROD',
         deployProc: 'deploy',
         deployVersions: 'DEMO:${BUILD_NUMBER}',
         deployOnlyChanged: false
       ]
     ])
   }
 }

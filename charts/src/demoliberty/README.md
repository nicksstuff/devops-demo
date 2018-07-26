![Liberty logo](https://pbs.twimg.com/media/DFSr-oYXkAAuMWh.jpg)

# IBM WebSphere Application Server Liberty Profile


## Getting Help

More documentation can be found [here](https://developer.ibm.com/wasdev/websphere-liberty/).

#IMPORTANT

You must label your nodes with:
```bash
kubectl get nodes

kubectl label nodes <node-name> placement=local
or
kubectl label nodes <node-name> placement=cloud
```

# Introduction

This chart deploys a single Liberty Demo App instance into an IBM Cloud private or other Kubernetes environment.

## Prerequisites

- Kubernetes 1.7 or greater, with beta APIs enabled

## Installing the Chart

To install the chart with the release name `demoliberty`:

```sh
helm install --name demoliberty LIBERTY/demoliberty
```

> **Tip**: See all the resources deployed by the chart using `kubectl get all -l release=demoliberty`

## Uninstalling the Chart

To uninstall/delete the `demoliberty` release:

```sh
helm delete demoliberty
```

The command removes all the Kubernetes components associated with the chart, except any Persistent Volume Claims (PVCs).  This is the default behavior of Kubernetes, and ensures that valuable data is not deleted.  In order to delete the PVC use the following command:

```sh
kubectl delete pvc -l release=demoliberty
```

# Default Node Port
nodePort: 32233

# Copyright

© Copyright Niklaus Hirt 2018

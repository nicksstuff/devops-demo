FROM websphere-liberty:webProfile7
ADD ./target/demo.war /config/dropins/

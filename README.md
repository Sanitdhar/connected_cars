# connected_cars
Using Here Maps Places Search API's , build an application to find parking spots, charging stations and restaurants near the user provided location 

Run as docker container:
Pre-request:
Docker should be up and running in local Environment.

Steps:
1. Navigate to connected_cars/locsearch folder and run below command. 'docker build -t location-search .' 
  OR 'docker pull sanitdhar25/locationsearch' followed by 'docker tag sanitdhar25/locationsearch location-search'
2. To run docker image 'docker run --name=locsearch -p 2020:2020 location-search'

To test the api in local, hit the below url:
http://localhost:2020/discover?name=Brandenburge%20Tor,Berlin


FROM docker.elastic.co/elasticsearch/elasticsearch:5.6.8
RUN elasticsearch-plugin remove x-pack
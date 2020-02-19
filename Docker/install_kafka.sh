#!/bin/bash

yum install -y java-1.8.0-openjdk vim
curl http://apache.rediris.es/kafka/2.4.0/kafka_2.11-2.4.0.tgz -o kafka_2.11-2.4.0.tgz
tar xzf kafka_2.11-2.4.0.tgz -C /opt/
ln -s /opt/kafka_2.11-2.4.0/ /opt/kafka
echo 'export KAFKA_HOME=/opt/kafka' >> ~/.bashrc
echo 'export PATH=$PATH:$KAFKA_HOME/bin' >> ~/.bashrc
source ~/.bashrc

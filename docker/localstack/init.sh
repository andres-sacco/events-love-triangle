#!/usr/bin/env bash

set -euo pipefail

# enable debug
# set -x

aws configure set aws_access_key_id "test"
aws configure set aws_secret_access_key "test"

echo "configuring sns/sqs"
echo "==================="
# https://gugsrs.com/localstack-sqs-sns/
LOCALSTACK_HOST=localhost
AWS_REGION=us-east-1
LOCALSTACK_DUMMY_ID=000000000000

get_all_queues() {
  awslocal --endpoint-url=http://${LOCALSTACK_HOST}:4566 sqs list-queues
}

create_queue() {
  local QUEUE_NAME_TO_CREATE=$1
  awslocal --endpoint-url=http://${LOCALSTACK_HOST}:4566 sqs create-queue --queue-name ${QUEUE_NAME_TO_CREATE} --attributes FifoQueue=true,ContentBasedDeduplication=true
}

get_all_topics() {
  awslocal --endpoint-url=http://${LOCALSTACK_HOST}:4566 sns list-topics
}

create_topic() {
  local TOPIC_NAME_TO_CREATE=$1
  awslocal --endpoint-url=http://${LOCALSTACK_HOST}:4566 sns create-topic --name ${TOPIC_NAME_TO_CREATE} --attributes FifoTopic=true,ContentBasedDeduplication=true
}

link_queue_and_topic() {
  local TOPIC_ARN_TO_LINK=$1
  local QUEUE_ARN_TO_LINK=$2
  awslocal --endpoint-url=http://${LOCALSTACK_HOST}:4566 sns subscribe --topic-arn ${TOPIC_ARN_TO_LINK} --protocol sqs --notification-endpoint ${QUEUE_ARN_TO_LINK} --attributes RawMessageDelivery=true
}

guess_queue_arn_from_name() {
  local QUEUE_NAME=$1
  echo "arn:aws:sqs:${AWS_REGION}:${LOCALSTACK_DUMMY_ID}:$QUEUE_NAME"
}

guess_topic_arn_from_name() {
  local TOPIC_NAME=$1
  echo "arn:aws:sns:${AWS_REGION}:${LOCALSTACK_DUMMY_ID}:$TOPIC_NAME"
}

PAYMENTS_IN_PROCESS_QUEUE_NAME="payments_in_process.fifo"
PAYMENTS_CONFIRMED_QUEUE_NAME="payments_confirmed.fifo"
RESERVATION_CONFIRMED_TOPIC_NAME="reservation_confirmed.fifo"
ASSERTIONS_QUEUE_NAME="reservation_confirmed-assertions.fifo"

echo "creating queue: $PAYMENTS_IN_PROCESS_QUEUE_NAME"
QUEUE_ARN=$(create_queue ${PAYMENTS_IN_PROCESS_QUEUE_NAME})
echo "created queue: $QUEUE_ARN"


echo "creating queue: $PAYMENTS_CONFIRMED_QUEUE_NAME"
QUEUE_ARN=$(create_queue ${PAYMENTS_CONFIRMED_QUEUE_NAME})
echo "created queue: $QUEUE_ARN"


echo "creating topic: $RESERVATION_CONFIRMED_TOPIC_NAME"
TOPIC_ARN=$(create_topic ${RESERVATION_CONFIRMED_TOPIC_NAME})
echo "created topic: $TOPIC_ARN"

echo "creating queue: $ASSERTIONS_QUEUE_NAME"
QUEUE_ARN=$(create_queue ${ASSERTIONS_QUEUE_NAME})
echo "created queue: $QUEUE_ARN"


echo "linking topic $RESERVATION_CONFIRMED_TOPIC_NAME to queue $ASSERTIONS_QUEUE_NAME"
LINKING_RESULT=$(link_queue_and_topic $(guess_topic_arn_from_name $RESERVATION_CONFIRMED_TOPIC_NAME) $(guess_queue_arn_from_name $ASSERTIONS_QUEUE_NAME))
echo "linking done:"
echo "$LINKING_RESULT"


echo "all topics are:"
echo "$(get_all_topics)"

echo "all queues are:"
echo "$(get_all_queues)"

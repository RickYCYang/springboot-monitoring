# send http request to springboot application for testing the metrics

hostname=http://127.0.0.1:62931
echo "Start sending HTTP requests to $hostname"
echo "-------------------------"
for i in {1..50}
do
  echo "Request #$i"
  result1=$(curl -s -w "\n" $hostname/api/users)
  echo "Result 1: $result1"
  # sleep 0.1
  result2=$(curl -s -w "\n" $hostname/api/users/hello)
  echo "Result 2: $result2"
  # sleep 0.1
  echo "-------------------------"
done
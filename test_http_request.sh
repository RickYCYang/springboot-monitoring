# send http request to springboot application for testing the metrics
# hostname=http://127.0.0.1:55984
hostname=http://127.0.0.1:49467
echo "Start sending HTTP requests to $hostname"
echo "-------------------------"
for i in {1..20}
do
  echo "Request #$i"
  result1=$(curl -s -w "\n" $hostname/api/users)
  echo "Result 1: $result1"
  sleep 0.1
  result2=$(curl -s -w "\n" $hostname/api/users/hello)
  echo "Result 2: $result2"
  sleep 0.1
  result3=$(curl -s -w "\n" $hostname/api/users/id/4)
  echo "Result 3: $result3"
  sleep 0.1
  result4=$(curl -s -w "\n" $hostname/api/users/3)
  echo "Result 4: $result4"
  sleep 0.1
  result5=$(curl -s -w "\n" $hostname/api/posts)
  echo "Result 5: $result5"
  # sleep 0.1
  # result6=$(curl -s -w "\n" $hostname/api/posts/self)
  # echo "Result 5, $result6"
  # echo "-------------------------"
done
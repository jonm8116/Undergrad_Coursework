if [ $1 == "gateway" ]; then
    ssh -i private.pem ubuntu@130.245.170.128

elif [ $1 == "search" ]; then
    ssh -i private.pem -t ubuntu@130.245.170.128 ssh -i private.pem ubuntu@192.168.122.15

elif [ $1 == "es" ]; then
    ssh -i private.pem -t ubuntu@130.245.170.128 ssh -i private.pem ubuntu@192.168.122.22

elif [ $1 == "db" ]; then
    ssh -i private.pem -t ubuntu@130.245.170.128 ssh -i private.pem ubuntu@192.168.122.17

elif [ $1 == "hw" ]; then 
    ssh -i private.pem -t ubuntu@130.245.170.128 ssh -i private.pem ubuntu@192.168.122.27

elif [ $1 == "users" ]; then
    ssh -i private.pem -t ubuntu@130.245.170.128 ssh -i private.pem ubuntu@192.168.122.13

elif [ $1 == "tweets" ]; then
    ssh -i private.pem -t ubuntu@130.245.170.128 ssh -i private.pem ubuntu@192.168.122.23

elif [ $1 == "cass" ]; then
    ssh -i private.pem -t ubuntu@130.245.170.128 ssh -i private.pem ubuntu@192.168.122.24

elif [ $1 == "media" ]; then
    ssh -i private.pem -t ubuntu@130.245.170.128 ssh -i private.pem ubuntu@192.168.122.25

fi

# install node js
sudo apt-get update
sudo apt-get install -y nodejs
sudo apt-get install -y npm

npm install -y querystring
npm install -y fs
npm install -y path
npm install -y express 
npm install -y body-parser
npm install -y mongodb
npm install -y handlebars
npm install -y nodemailer
npm install -y jsonwebtoken
npm install -y elasticsearch
npm install -y multer

# Setup node properly
curl -sL https://deb.nodesource.com/setup_8.x | sudo -E bash -
sudo apt-get install -y nodejs

# install nginx 
sudo apt-get install -y nginx

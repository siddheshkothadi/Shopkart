# Setting up git configurations
git config --global user.name "siddheshkothadi"
git config --global user.email "realsiddheshko@gmail.com"

# Make a directory
mkdir ~/apk

# Copy apk
cp app/build/outputs/apk/debug/app-debug.apk ~/apk

# Change git branch
git fetch origin
git checkout apk

# Move apk
# mv ~/apk/app-debug.apk ~
pwd

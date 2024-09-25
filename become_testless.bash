#!/bin/bash

# Check if the current branch is 'dev'
current_branch=$(git branch --show-current)
if [ "$current_branch" != "dev" ]; then
  echo "Error: You must be on the 'dev' branch."
  exit 1
fi

# Remove the 'testless_dev' branch if it exists
git branch -D testless_dev || echo "No 'testless_dev' branch to remove."

# Pull the latest changes from the remote
git pull || exit 1

# Create a new branch 'testless'
git checkout -b testless || exit 1

# Remove all files ending with 'Test.java'
git rm -f *Test.java || exit 1

# Commit the changes
git commit -m 'forked the last `dev` state' || exit 1

# Exit with the last command status
exit $?

#!/usr/bin/env bash

success_runcrud() {
  echo "script done"
}

start_safari() {
  open http://www.google.com
}

go_to_page() {
  open http://localhost:8080/crud/v1/tasks
}

fail() {
  echo "script failed"
}

if sudo sh /Users/lukaszmarchel/IdeaProjects/CRUD/task/runcrud.sh; then
  success_runcrud
  start_safari
  go_to_page
else
  fail
fi

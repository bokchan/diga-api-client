## Development on host machine

Linux/Ubuntu

    sudo apt update
    sudo apt install openjdk-11-jdk && maven -y

    # If multiple java versions are installed, you might need to choose the active installation
    sudo update-alternatives --config java

### VS Code

- Install the extension pack: https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack
- Open the action "Java: Configure Java Runtime" and check the correct Java version is selected.

## Development in docker-compose container

Requires that `docker-compose` is installed on the host machine.

Build the container with `openjdk 11`:

    docker-compose build

Start the container and open a shell.

    # for persistent container running in the background
    docker-compose up -d
    docker-compose exec diga-api-client bash

    # or for non-persistent container
    docker-compose run --rm  diga-api-client


## Using with remote container in VSCode

- Start the container.
- Open the action "Attach to Running Container..." and select the running container
- Open the action "Maven: Execute Commands..." to select a command to run.



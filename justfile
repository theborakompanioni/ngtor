# This justfile requires https://github.com/casey/just

# Load environment variables from `.env` file.
set dotenv-load
# Fail the script if the env file is not found.
set dotenv-required

project_dir := justfile_directory()
build_dir := project_dir + "/demented/build/libs"
app_uber_jar_pattern := build_dir + "/*-boot.jar"

# print available targets
[group("project-agnostic")]
default:
    @just --list --justfile {{justfile()}}

# evaluate and print all just variables
[group("project-agnostic")]
evaluate:
    @just --evaluate

# print system information such as OS and architecture
[group("project-agnostic")]
system-info:
    @echo "architecture: {{arch()}}"
    @echo "os: {{os()}}"
    @echo "os family: {{os_family()}}"

# clean (remove) the build artifacts
[group("development")]
clean:
    @./gradlew clean

# compile the project
[group("development")]
build:
    @./gradlew build -x test

# list dependency tree of this project
[group("development")]
dependencies:
    @./gradlew dependencyTree

# run unit tests
[group("development")]
test:
    @./gradlew test

# run integration tests
[group("development")]
test-integration:
    @./gradlew integrationTest --rerun-tasks --no-parallel

# run all tests
[group("development")]
test-all:
    @./gradlew test integrationTest --rerun-tasks --no-parallel

# build javadocs
[group("development")]
javadoc:
    @./gradlew javadoc -PjavadocEnabled

# package the app to create an uber jar
[group("development")]
package:
    @./gradlew bootJar --rerun-tasks

# check style
[group("development")]
checkstyle:
    @./gradlew checkstyleMain checkstyleTest checkstyleIntegTest

# spot bugs
[group("development")]
spotbugs:
    @./gradlew spotbugsMain spotbugsTest spotbugsIntegTest

# lint files
[group("development")]
lint:
    @./gradlew autoLintGradle --no-parallel

# update metadata for dependency verification
[group("development")]
update-verification *args='':
    @./gradlew \
      -Dorg.gradle.caching=false \
      -Dorg.gradle.configureondemand=false \
      -Dorg.gradle.parallel=false \
      dependencies \
      --write-verification-metadata pgp,sha256 --export-keys --write-locks {{args}}



<p align="center">
    <img src="https://github.com/theborakompanioni/ngtor/blob/master/docs/assets/images/logo-sm.png" alt="Logo" width="255" />
</p>


ngtor
===
Easily expose local services via Tor

- Tunnel traffic via Tor to your locally running service
- Serve static via Tor with a single command
- All-in-one binary for macos, linux and windows

**Note**: This software is functional, but in early development. Expect breaking changes till v1.0 is reached.

## Table of Contents

- [Install](#install)
- [Commands](#commands)
- [Development](#development)
- [Contributing](#contributing)
- [Alternatives](#alternatives)
- [Resources](#resources)
- [License](#license)


## Install
[Download](https://jitpack.io/#theborakompanioni/ngtor) from Jitpack.


## Commands

### `http`
Serve the specified port via Tor (default is `8080`).

```shell script
$ ngtor.jar http --port=3000
```
```
Setting up hidden service 'ngtor_main' for port 3000
✔ Service 'ngtor_main' to 127.0.0.1:3000 activated on: http://0123456789abcdef.onion
```

### `demo`
Spin up a webserver and serve a demo page via tor.

```shell script
$ ngtor.jar demo
```
```
2009-01-03 22:00:10.937  INFO [...] : Starting NgtorApplication v0.1.0-dev
2009-01-03 22:00:12.208  INFO [...] : Starting Tor
2009-01-03 22:00:15.427  INFO [...] : Started NgtorApplication in 6.972 seconds (JVM running for 7.538)
2009-01-03 22:00:15.505  INFO [...] : =================================================
2009-01-03 22:00:15.505  INFO [...] : url: http://0123456789abcdef.onion
2009-01-03 22:00:15.507  INFO [...] : virtual host: 0123456789abcdef.onion
2009-01-03 22:00:15.507  INFO [...] : virtual port: 80
2009-01-03 22:00:15.507  INFO [...] : host: 127.0.0.1
2009-01-03 22:00:15.507  INFO [...] : port: 8080
2009-01-03 22:00:15.507  INFO [...] : directory: /home/tbk/.ngtor/tor-working-dir/ngtor_demo
2009-01-03 22:00:15.507  INFO [...] : -------------------------------------------------
2009-01-03 22:00:15.507  INFO [...] : Or browse to http://0123456789abcdef.onion in Tor Browser, or
2009-01-03 22:00:15.507  INFO [...] : run: `torsocks -p 38155 curl http://0123456789abcdef.onion/index.html -v`
2009-01-03 22:00:15.508  INFO [...] : =================================================
```

### `check`
Performs some health checks, e.g. if outbound connection via Tor can be established.

```shell script
$ ngtor.jar check
```
```
Check started..
✔ Tor is working.
Check finished after 1.452 s
```

### `help`
Print help text.

```shell script
$ ngtor.jar help
```
```
Usage: ./ngtor.jar COMMAND [OPTION...]

  Command
      http                serve the specified port
      demo                spin up a webserver and serve a demo page via tor
      check               perform system health checks (e.g. tor connection)
      version             print application version
      help                print this help text

  Examples
      ./ngtor.jar http             # onion url for port 8080 (default) web server
      ./ngtor.jar http --port=80   # onion url for port 80 web server

  Version
      0.1.0-dev
```


## Development

### Requirements
- java >=17

### Build
```shell script
./gradlew build -x test
```

### Run
```shell script
./gradlew -p ngtor/ngtor-app bootRun --args='check'
```
or
```shell script
./ngtor/ngtor-app/build/libs/ngtor.jar check
```

### Test
```shell script
./gradlew test integrationTest
```

### Dependency Verification
Gradle is used for checksum and signature verification of dependencies.

```shell script
# write metadata for dependency verification
./gradlew --write-verification-metadata pgp,sha256 --export-keys
```

See [Gradle Userguide: Verifying dependencies](https://docs.gradle.org/current/userguide/dependency_verification.html)
for more information.

### Checkstyle
[Checkstyle](https://github.com/checkstyle/checkstyle) with adapted [google_checks](https://github.com/checkstyle/checkstyle/blob/master/src/main/resources/google_checks.xml)
is used for checking Java source code for adherence to a Code Standard.

```shell script
# check for code standard violations with checkstyle
./gradlew checkstyleMain
```

### SpotBugs
[SpotBugs](https://spotbugs.github.io/) is used for static code analysis.

```shell script
# invoke static code analysis with spotbugs
./gradlew spotbugsMain
```


## Contributing
All contributions and ideas are always welcome. For any question, bug or feature request,
please create an [issue](https://github.com/theborakompanioni/ngtor/issues).
Before you start, please read the [contributing guidelines](contributing.md).


## Alternatives
See [Awesome Tunneling](https://github.com/anderspitman/awesome-tunneling) by [@anderspitman](https://github.com/anderspitman) for other pretty cool tools that might fit your needs.


## Resources

- Tor: https://www.torproject.org/
- Spring Boot (GitHub): https://github.com/spring-projects/spring-boot
---
- tor-binary (GitHub): https://github.com/bisq-network/tor-binary
- netlayer (GitHub): https://github.com/bisq-network/netlayer
- tor-spring-boot-starter (GitHub): https://github.com/theborakompanioni/tor-spring-boot-starter


## License

The project is licensed under the Apache License. See [LICENSE](LICENSE) for details.

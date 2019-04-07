<p align="center">
  This library is presented by:
  <a href="https://www.spigotcourse.org/?utm_source=github&utm_medium=github">
    <img src="https://i.imgur.com/Xr0p2g3.png" />
  </a>
</p>

---

![Logo](https://i.imgur.com/GEyZoJV.png)
<p align="center"><h2>Create complex menu systems and beautiful GUIs in Minecraft with easy.</h2></p>

Designer uses [Remain](https://github.com/kangarko/Remain) to maintain the perfect cross-version Minecraft compatibility.

## Installation
We use Maven to compile. See below for a step-by-step tutorial.


1. Place this to your repositories:

```xml
<repository>
	<id>jitpack.io</id>
	<url>https://jitpack.io</url>
</repository>
```

2. Place this to your dependencies:

```xml
<dependency>
	<groupId>com.github.kangarko</groupId>
	<artifactId>Designer</artifactId>
	<version>2.0.0</version> <!-- change to the latest version -->
	<scope>compile</scope>
</dependency>
 ```
 
3. **Set your plugin's instance to hook into Designer by calling Designer#setPlugin(JavaPlugin).**

4. Make sure that the library shades into your final .jar when you compile your plugin. Here is an example of a shade plugin that will do it for you:

IF YOU ALREADY HAVE A SHADE PLUGIN, ONLY USE THE RELOCATION SECTION FROM BELOW.

```xml
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-shade-plugin</artifactId>
	<version>3.1.1</version>
	<executions>
		<execution>
			<phase>package</phase>
			<goals>
				<goal>shade</goal>
			</goals>
		</execution>
	</executions>
	<configuration>
		<createDependencyReducedPom>false</createDependencyReducedPom>
		<relocations>
			<relocation>
				<pattern>org.mineacademy.designer</pattern>
				<shadedPattern>your.package.yourplugin.designer</shadedPattern>
			</relocation>
		</relocations>
	</configuration>
</plugin>
```

## Compiling

Compiling is simple and requires Maven. Download this repo as a zip, import as Maven project and run "clean install" target.

However, you also need to download Remain (it's free) or edit the following dependency:

```xml
<dependency>
	<groupId>org.mineacademy.remain</groupId> <!-- Change this to com.github.kangarko -->
	<artifactId>Remain</artifactId>
	<version>LATEST</version> <!-- Change this to the latest version -->
	<scope>compile</scope>
</dependency>
```

Copyright (C) 2019 | All Rights Reserved. Commercial and non-commercial use allowed as long as you provide a clear link on your (sales) page that you are using this library.  

**Sample of run configuration**

```
<configuration name="MagicDraw" type="Application" factoryName="Application">
       <extension name="coverage" enabled="false" merge="false" sample_coverage="true" runner="idea" />
       <option name="MAIN_CLASS_NAME" value="com.nomagic.magicdraw.LaunchGateway" />
       <option name="VM_PARAMETERS" value="-Xmx2000M -Xss1024K -XX:PermSize=60M -XX:MaxPermSize=200M -DLOCALCONFIG=true -Dmd.plugins.dir=${MAGIC_DRAW_INSTALL_DIRECTORY}/plugins;&quot;C:\Users\Justinas Greibus\IdeaProjects\webpackStats2MagicDraw\plugins&quot; -jar ${MAGIC_DRAW_INSTALL_DIRECTORY}/openapi/ide/lib/com.nomagic.magicdraw.intellij.launcher.jar" />
       <option name="PROGRAM_PARAMETERS" value="-verbose" />
       <option name="WORKING_DIRECTORY" value="file://$MAGIC_DRAW_INSTALL_DIRECTORY$" />
       <option name="ALTERNATIVE_JRE_PATH_ENABLED" value="false" />
       <option name="ALTERNATIVE_JRE_PATH" />
       <option name="ENABLE_SWING_INSPECTOR" value="false" />
       <option name="ENV_VARIABLES" />
       <option name="PASS_PARENT_ENVS" value="true" />
       <module name="webpackStats2MagicDraw" />
       <envs />
</configuration>
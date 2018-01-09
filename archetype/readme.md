# archetype工程使用方法：
* 创建一个maven工程，点击下一步
![创建工程](../docs/img/create-mvn-prj.jpg)
* 选择一个工程保存目录，点击下一步
![配置工程目录](../docs/img/select-prj-folder.jpg)
* 点击Catalog选择Default Local，勾选Include snapshot archetypes，然后在窗口中间列表中的选择com.lish.fast并点击下一步
![选择archetype](../docs/img/select-arch.jpg)
 * 填写Group Id（项目目录名），Artifact Id（应用名），点击下一步
![配置工程属性](../docs/img/set-prj-prop.jpg)
 * 导入SQL
 导入父工程的根目录下fast-boot-security.sql文件到数据库
 * 修改配置文件
 根据项目需求修改XXX-web工程下resources目录下application.yml文件中datasource配置
# 注意事项：
* 生成的app和web模块工程名需要手工修改，每个工程pom.xml文件中工程的artifactId也需要做相应的修改
* 父工程的pom.xml文件中module部分也需要修改为对应模块的artifactId

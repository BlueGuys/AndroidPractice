pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

gradle.settingsEvaluated {
    println("gradle settingsEvaluated settings.gradle 解析完成之后被调用")
}
gradle.projectsLoaded {
    println("gradle projectsLoaded 参与构建的 Project 对象创建完毕之后被调用")
}
gradle.beforeProject {
    println("gradle beforeProject 每个Project执行build.gradle配置代码之前被调用")
}
gradle.projectsEvaluated {
    println("gradle projectsEvaluated 所有Project都执行完对应的build.gradle配置代码，准备生成对应的Task依赖图")
}
gradle.taskGraph.whenReady {
    println("gradle taskGraph whenReady Task 依赖关系已经建立完毕")
}
gradle.taskGraph.beforeTask {
    println("gradle taskGraph beforeTask 每一个 Task 执行之前被调用")
}
gradle.taskGraph.afterTask {
    println("gradle taskGraph afterTask 每一个 Task 执行之后被调用")
}
gradle.buildFinished {
    println("gradle buildFinished 构建结束之后被调用")
}


rootProject.name = "AndroidPractice"
include(":app")


import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.kapt(vararg dependencyNotation:String) {
    dependencyNotation.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.platformImplementations(vararg list: String){
    list.forEach { dependency ->
        add("implementation", platform(dependency))
    }
}

fun DependencyHandler.implementations(vararg list: String) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}
fun DependencyHandler.implementation(dependencyNotation: String) {
    dependencyNotation.apply {
        add("implementation", this)
    }
}
fun DependencyHandler.platformAndroidTestImplementation(vararg list: String) {
    list.forEach { dependency ->
        add("androidTestImplementation", platform(dependency))
    }
}
fun DependencyHandler.androidTestImplementation(vararg list: String) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(vararg list: String) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}

fun DependencyHandler.debugImplementation(vararg list: String) {
    list.forEach { dependency ->
        add("debugImplementation", dependency)
    }
}


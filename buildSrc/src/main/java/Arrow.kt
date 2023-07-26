import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.addArrowDependency(){
    implementation("io.arrow-kt:arrow-fx-coroutines:1.0.1")
}
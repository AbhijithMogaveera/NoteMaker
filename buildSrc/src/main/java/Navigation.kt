import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.addNavigationDependency(){
    implementation ("androidx.navigation:navigation-fragment:${Versions.nav_version}")
    implementation ("androidx.navigation:navigation-ui:${Versions.nav_version}")
}
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.addHiltDependency(){
    implementation ("com.google.dagger:hilt-android:2.44")
    kapt ("com.google.dagger:hilt-compiler:2.44")

}
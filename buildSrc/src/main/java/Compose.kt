import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.addComposeDependency(){
    implementation ("androidx.activity:activity-compose:1.5.1")
    platformImplementations("androidx.compose:compose-bom:2022.10.00")
    implementation ("androidx.compose.ui:ui")
    implementation ("androidx.compose.ui:ui-graphics")
    implementation ("androidx.compose.ui:ui-tooling-preview")
    platformAndroidTestImplementation( "androidx.compose:compose-bom:2022.10.00")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4")
    debugImplementation ("androidx.compose.ui:ui-tooling")
    debugImplementation ("androidx.compose.ui:ui-test-manifest")
}
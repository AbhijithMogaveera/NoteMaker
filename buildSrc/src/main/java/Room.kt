import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.addRoomDependency(){
    kapt("androidx.room:room-compiler:${Versions.room_version}")
    implementation("androidx.room:room-runtime:${Versions.room_version}")
    implementation("androidx.room:room-ktx:${Versions.room_version}")
}
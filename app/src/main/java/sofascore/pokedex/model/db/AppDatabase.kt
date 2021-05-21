package sofascore.pokedex.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import sofascore.pokedex.model.Pokemon

@Database(
    entities = [Pokemon::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun PokemonDao(): PokemonDao

    companion object {
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (instance == null) {
                instance = buildDatabase(context)
            }
            return instance as AppDatabase
        }

        private fun buildDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "AppDatabaseRoom.db"
            ).build()


    }
}
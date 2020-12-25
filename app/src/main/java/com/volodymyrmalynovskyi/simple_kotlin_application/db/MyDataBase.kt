package com.volodymyrmalynovskyi.simple_kotlin_application.db

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.volodymyrmalynovskyi.simple_kotlin_application.db.models.NoteRecord
import com.volodymyrmalynovskyi.simple_kotlin_application.db.models.NoteRecordDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Database(entities = [NoteRecord::class], version = 1)
abstract class MyDataBase: RoomDatabase(){

    abstract fun noteRecordDao(): NoteRecordDao

    companion object{
        @Volatile
        private var instance: MyDataBase? = null

        fun getDataBase(context: Context): MyDataBase{
            return instance?:
                    synchronized(MyDataBase::class){
                        instance = Room.databaseBuilder(context.applicationContext, MyDataBase::class.java, "my_db").build()
                        return instance as MyDataBase
                    }
        }
    }
}

@InstallIn(ApplicationComponent::class)
@Module
class DatabaseProvider{
    @Singleton
    @Provides
    fun provideDatabase(app: Application) = MyDataBase.getDataBase(app)

    @Singleton
    @Provides
    fun provideNoteRecordDao(db: MyDataBase) = db.noteRecordDao()
}
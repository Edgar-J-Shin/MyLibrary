package com.sendbird.mylibrary.data.local.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.sendbird.mylibrary.model.Book

@Entity(tableName = "books", indices = [Index(value = ["id"], unique = true)])
class BookEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,
    val image: String,
    val isbn13: String,
    val price: String,
    val subtitle: String,
    val title: String,
    val url: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(image)
        parcel.writeString(isbn13)
        parcel.writeString(price)
        parcel.writeString(subtitle)
        parcel.writeString(title)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BookEntity> {
        override fun createFromParcel(parcel: Parcel): BookEntity {
            return BookEntity(parcel)
        }

        override fun newArray(size: Int): Array<BookEntity?> {
            return arrayOfNulls(size)
        }
    }
}

fun BookEntity.mapToProduct(): Book = Book(
    image,
    isbn13,
    price,
    subtitle,
    title,
    url
)
package com.marcelosmith77.android.baselibrary.contact;


import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;

import java.io.IOException;
import java.io.InputStream;

public class ContactBuilder {

    private Context context;
    private ContactInfo contactInfo;
    private Uri uriContact;
    private String contactID;

    public ContactBuilder(Context context, Uri uriContact) {
        this.context = context;
        this.uriContact = uriContact;
        this.contactInfo = new ContactInfo();

        retrieveContactID();
    }


    public ContactBuilder retrieveContactPhoto() {

        try {
            InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(),
                    ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, new Long(contactID)));

            if (inputStream != null) {
                Bitmap photo = BitmapFactory.decodeStream(inputStream);
                contactInfo.setPhoto(photo);
            }

            assert inputStream != null;
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return this;
    }

    private void retrieveContactID() {

        // getting contacts ID
        Cursor cursorID = context.getContentResolver().query(uriContact,
                new String[]{ContactsContract.Contacts._ID},
                null, null, null);

        if (cursorID.moveToFirst()) {

            contactID = cursorID.getString(cursorID.getColumnIndex(ContactsContract.Contacts._ID));
            contactInfo.setId(contactID);
        }

        cursorID.close();

    }

    public ContactBuilder retrieveContactNumber() {

        // Using the contact ID now we will get contact phone number
        Cursor cursorPhone = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},

                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? AND " +
                        ContactsContract.CommonDataKinds.Phone.TYPE + " = " +
                        ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE,

                new String[]{contactID},
                null);

        if (cursorPhone.moveToFirst()) {
            String contactNumber = cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contactInfo.setNumber(contactNumber);
        }

        cursorPhone.close();

        return this;
    }


    public ContactBuilder retrieveContactName() {

        // querying contact data store
        Cursor cursor = context.getContentResolver().query(uriContact, null, null, null, null);

        if (cursor.moveToFirst()) {

            // DISPLAY_NAME = The display name for the contact.
            // HAS_PHONE_NUMBER =   An indicator of whether this contact has at least one phone number.

            String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            contactInfo.setName(contactName);
        }

        cursor.close();

        return this;
    }

    public ContactBuilder retrieveContactEmail() {

        // Using the contact ID now we will get contact phone number
        Cursor cursorPhone = context.getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Email.DATA},

                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? ",
                new String[]{contactID},
                null);

        if (cursorPhone.moveToFirst()) {
            String contactEmail = cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
            contactInfo.setEmail(contactEmail);
        }

        cursorPhone.close();

        return this;
    }

    public ContactInfo build() {
        return this.contactInfo;
    }
}

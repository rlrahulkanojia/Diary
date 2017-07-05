package com.example.root.app;

/**
 * Created by root on 27/6/17.
 */

public class Record {

    // private variables
    public int _id;
    public String _name;
    public String _date;
    public String _text;

    public Record() {
    }

    // constructor
    public Record(int id, String _name, String _date, String _text) {
        this._id = id;
        this._name = _name;
        this._date = _date;
        this._text = _text;

    }

    // constructor
    public Record(String _name, String _date, String _text) {
        this._name = _name;
        this._date = _date;
        this._text = _text;
    }

    // getting ID
    public int getID() {
        return this._id;
    }

    // setting id
    public void setID(int id) {
        this._id = id;
    }

    // getting name
    public String getName() {
        return this._name;
    }

    // setting name
    public void setName(String name) {
        this._name = name;
    }

    // getting phone number
    public String getDate() {
        return this._date;
    }

    // setting phone number
    public void setDate(String date) {
        this._date = date;
    }

    // getting email
    public String gettext() {
        return this._text;
    }

    // setting email
    public void settext(String text) {
        this._text = text;
    }

}
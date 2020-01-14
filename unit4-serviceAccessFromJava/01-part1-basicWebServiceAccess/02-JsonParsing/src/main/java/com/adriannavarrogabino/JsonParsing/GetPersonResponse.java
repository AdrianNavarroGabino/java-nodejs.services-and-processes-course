package com.adriannavarrogabino.JsonParsing;

import com.google.gson.annotations.SerializedName;

public class GetPersonResponse {
    @SerializedName("error")
    boolean haserror;
    Person person;

    public boolean getError() {
        return haserror;
    }

    public Person getPerson() {
        return person;
    }
}


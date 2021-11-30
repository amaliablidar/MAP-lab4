package com.company.Repository;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class InFileRepo<T> implements CrudRepo<T>{
    protected ArrayList<T> repoList;

    public InFileRepo(List<T> repoList) throws FileNotFoundException {
        this.repoList = (ArrayList<T>) readDataFromFile();
    }
    public abstract List<T> readDataFromFile()throws FileNotFoundException;
    public abstract  void writeToFile() throws IOException;
}
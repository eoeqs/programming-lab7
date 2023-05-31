package me.lab7.server.commands;

import me.lab7.common.network.Response;
import me.lab7.common.models.Worker;
import me.lab7.server.managers.CollectionManager;

public class Insert implements Command {

    private final CollectionManager collectionManager;

    public Insert(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Object arg) {
        Worker worker = (Worker) arg;
        long key = worker.getId();
        if (collectionManager.workerMap().containsKey(key)) {
            return new Response("The collection already contains an element with key = " + key + ".\n");
        }
        return new Response(collectionManager.add(worker));
    }

    @Override
    public String name() {
        return "insert";
    }

    @Override
    public String argDesc() {
        return "{key (long value)}";
    }

    @Override
    public String desc() {
        return "add a new element to the collection using the given key";
    }
}
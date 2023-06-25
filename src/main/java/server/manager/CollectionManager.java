package server.manager;

import common.data.LabWork;

import java.time.LocalDate;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * class of working with the TreeSet collection
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public class CollectionManager {
    private final NavigableSet<LabWork> labWorks = new TreeSet<>();
    private final LocalDate creatingCollection;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public CollectionManager() {
        creatingCollection = LocalDate.now();
    }


    public void addToCollection(LabWork newLab) {
        lock.writeLock().lock();
        labWorks.add(newLab);
        lock.writeLock().unlock();
    }

    public NavigableSet<LabWork> getLabWork() {
        return labWorks;
    }

    public void clearCollection(Long client) {
        lock.writeLock().lock();
        labWorks.removeIf(labWork -> labWork.getOwnerID().equals(client));
        lock.writeLock().unlock();
    }

    public boolean removeByID(Long removeLabWorkId, Long client) {
        lock.writeLock().lock();
        var did = labWorks.removeIf(i -> i.getId() == removeLabWorkId && i.getOwnerID().equals(client));
        lock.writeLock().unlock();
        return did;

    }

    public int removeGreater(Long id, Long client) {
        lock.writeLock().lock();
        var startSize = labWorks.size();
        labWorks.removeIf(i -> i.getId() > id && i.getOwnerID().equals(client));
        var count =  startSize -labWorks.size();
        lock.writeLock().unlock();
        return count;
    }

    public int removeLower(Long id, Long client) {
        lock.writeLock().lock();
        var startSize = labWorks.size();
       labWorks.removeIf(i -> i.getId() < id && i.getOwnerID().equals(client));
        var count =  startSize -labWorks.size();
        lock.writeLock().unlock();
        return count;
    }

    public LocalDate getCreatingCollection() {
        return creatingCollection;
    }

    @Override
    public String toString() {
        return "labWorks=" + labWorks;
    }


    public void initializeData(NavigableSet<LabWork> collection) {
        labWorks.addAll(collection);
    }

    public LabWork getElementById(Long updateLabWorkId, Long client) {
        lock.readLock().lock();
        LabWork a = labWorks.stream()
                .filter(i -> i.getId() == updateLabWorkId && i.getOwnerID().equals(client))
                .findFirst()
                .orElse(null);
        lock.readLock().unlock();
        return a;
    }

    public void update(LabWork labWork, Long client) {
        lock.writeLock().lock();
        if (labWorks.removeIf(i -> i.getId() == labWork.getId() && i.getOwnerID().equals(client)))
            labWorks.add(labWork);
        lock.writeLock().unlock();
    }
}

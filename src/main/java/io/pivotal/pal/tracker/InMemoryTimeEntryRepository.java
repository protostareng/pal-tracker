package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    private long currentId = 1L;

    private List<TimeEntry> timeEntries = new ArrayList<>();

    public TimeEntry create(TimeEntry timeEntry) {
        if (timeEntry.getId() == 0) timeEntry.setId(currentId++);
        timeEntries.add(timeEntry);
        return timeEntry;
    }

    public TimeEntry find(long id) {
        TimeEntry seekedResult = timeEntries.stream().filter(timeEntry -> timeEntry.getId() == id).findAny().orElse(null);
        return seekedResult;
    }

    public List<TimeEntry> list() {

        return timeEntries;
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        TimeEntry item = find(id);

        if (item == null) return null;
        else {
            item.setDate(timeEntry.getDate());
            item.setHours(timeEntry.getHours());
            item.setProjectId(timeEntry.getProjectId());
            item.setUserId(timeEntry.getUserId());


            return item;

        }

    }

    public void delete(long id) {
        timeEntries.removeIf(x -> x.getId() == id);
    }
}

package Model;

import java.time.LocalDateTime;

public class Evento {
    
    private Integer id;
    private String name;
    private String description;
    private LocalDateTime event_date;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private Integer created_by;
    private Integer updated_by;
    private boolean can_update;
    private boolean can_delete;
    
    Evento(int id, String name, String description, LocalDateTime event_date, LocalDateTime created_at, LocalDateTime updated_at, Integer created_by, Integer updated_by, boolean can_update, boolean can_delete) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.event_date = event_date;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.can_update = can_update;
        this.can_delete = can_delete;
    }  

    Evento() {
     
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getEvent_date() {
        return event_date;
    }

    public void setEvent_date(LocalDateTime event_date) {
        this.event_date = event_date;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public Integer getCreated_by() {
        return created_by;
    }

    public void setCreated_by(Integer created_by) {
        this.created_by = created_by;
    }

    public Integer getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(Integer updated_by) {
        this.updated_by = updated_by;
    }

    public boolean isCan_update() {
        return can_update;
    }

    public void setCan_update(boolean can_update) {
        this.can_update = can_update;
    }

    public boolean isCan_delete() {
        return can_delete;
    }

    public void setCan_delete(boolean can_delete) {
        this.can_delete = can_delete;
    }
    

    
}


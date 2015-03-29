/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


public class MassageBuilder {
    private int id;
    private String comment;
    private String startTime;
    private MassageType type;

    public MassageBuilder() {
    }

    public MassageBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public MassageBuilder setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public MassageBuilder setStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public MassageBuilder setType(MassageType type) {
        this.type = type;
        return this;
    }

    public Massage createMassage() {
        return new Massage(id, comment, startTime, type);
    }
    
}

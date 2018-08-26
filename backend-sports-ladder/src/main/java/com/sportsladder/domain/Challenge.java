package com.sportsladder.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class Challenge implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinColumn(name = "challengerId")
    private Player challenger;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinColumn(name = "defenderId")
    private Player defender;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinColumn(name = "winnerId")
    private Player winner;

    @Column(name="status")
    private Integer status;

    @Column(name="startDate")
    private LocalDateTime startDate;

    @Column(name="completionDate")
    private LocalDateTime completionDate;


    public Challenge(){};

    public Challenge(Player player1, Player player2){
        if(player1.getRank() > player2.getRank()) {
            this.challenger = player1;
            this.defender = player2;
        }
        else{
            this.challenger = player2;
            this.defender = player1;
        }
        this.startDate = LocalDateTime.now();
        this.status = 1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getChallenger() {
        return challenger;
    }

    public void setChallenger(Player challenger) {
        this.challenger = challenger;
    }

    public Player getDefender() {
        return defender;
    }

    public void setDefender(Player defender) {
        this.defender = defender;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (o.getClass() != Challenge.class)
            return false;
        Challenge otherChallenge = (Challenge) o;
        return(this.getId() != null && this.getId().equals(otherChallenge.getId()));
    }
}

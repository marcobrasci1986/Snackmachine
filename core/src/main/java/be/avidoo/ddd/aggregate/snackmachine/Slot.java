package be.avidoo.ddd.aggregate.snackmachine;

import be.avidoo.ddd.AbstractEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Slot extends AbstractEntity {

    @Embedded
    private SnackPile snackPile;
    @ManyToOne
    @JoinColumn(name = "fk_snackmachine")
    private SnackMachine snackMachine;
    private int position;

    public Slot(SnackMachine snackMachine, int position) {
        this.snackMachine = snackMachine;
        this.position = position;
        this.snackPile = SnackPile.EMPTY;
    }

    public void setSnackPile(SnackPile snackPile) {
        this.snackPile = snackPile;
    }
}

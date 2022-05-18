package be.avidoo.ddd.aggregate.snackmachine;

import be.avidoo.ddd.AbstractEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Slot extends AbstractEntity {

    @Embedded
    private SnackPile snackPile;
    @ManyToOne(fetch = FetchType.LAZY)
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

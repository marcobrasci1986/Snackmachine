package be.avidoo.ddd.boundedcontext.snackmachine.snackmachine;

import be.avidoo.ddd.common.AbstractEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Slot")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Slot extends AbstractEntity {

    @Embedded
    private SnackPile snackPile;

    private int position;

    public Slot(int position) {
        this.position = position;
        this.snackPile = SnackPile.EMPTY;
    }

    public void setSnackPile(SnackPile snackPile) {
        this.snackPile = snackPile;
    }
}

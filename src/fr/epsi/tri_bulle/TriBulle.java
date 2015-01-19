package fr.epsi.tri_bulle;

import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.chart.ScatterChart;

public class TriBulle extends Service<Boolean> {
    private ObservableList<ScatterChart.Data> tableau;

    public TriBulle(ObservableList<ScatterChart.Data> tableau) {
        this.tableau = tableau;
    }

    @Override
    protected Task<Boolean> createTask() {
        return new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                updateProgress(0, tableau.size());
                trier();
                updateProgress(tableau.size(), tableau.size());

                return true;
            }

            private void trier() {
                int progress = 0;
                for (int index = tableau.size() - 1; index > 0 && !isCancelled(); index--) {
                    updateProgress(progress++, tableau.size() - 1);

                    boolean echange = false;
                    for (int subIndex = 0; subIndex < index; subIndex++) {
                        if (getYValueAt(subIndex) > getYValueAt(subIndex + 1)) {
                            int tmp = getYValueAt(subIndex);
                            tableau.get(subIndex).setYValue(getYValueAt(subIndex + 1));
                            tableau.get(subIndex + 1).setYValue(tmp);

                            echange = true;
                        }
                    }

                    try {
                        Thread.sleep(5000 / tableau.size());
                    } catch (InterruptedException e) {

                    }

                    if (!echange) {
                        break;
                    }
                }
            }

            private int getYValueAt(int subIndex) {
                return ((Number) tableau.get(subIndex).getYValue()).intValue();
            }
        };
    }
}

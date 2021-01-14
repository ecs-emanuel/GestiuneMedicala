import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.util.List;

public class PaginaPacientIstoricMedical
{
    protected static void afisareIstoricMedical(JPanel panelBot, Pacient pacient)
    {
        List<FisaPacient> listaFisaPacient;
        listaFisaPacient = PacientRepository.getLisaFisaPacient(pacient);

        String columnNames[] = { "Index", "Medic", "Departament", "Diagnostic", "Tratament" };
        int columnSizes[] = { 40, 130, 130, 160, 300 };
        int totalColumns = columnNames.length;

        JTable table = new JTable(listaFisaPacient.size(), totalColumns);

        table.setRowHeight(25);
        TableColumnModel columnModel = table.getColumnModel();

        for (int i = 0; i < totalColumns; i++)
        {
            TableColumn column = columnModel.getColumn(i);
            column.setHeaderValue(columnNames[i]);
            column.setMinWidth(columnSizes[i]);
            column.setMaxWidth(columnSizes[i]);
        }

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(10, 12, 762, 430);
        panelBot.add(scroll);

        for (int i = 0; i < listaFisaPacient.size(); i++)
        {
            FisaPacient fisaPacient = listaFisaPacient.get(i);
            Medic medic = fisaPacient.getMedic();
            Specializare specializare = medic.getSpecializare();

            table.setValueAt(fisaPacient.getIndex(), i, 0);
            table.setValueAt(medic.getNume() + " " + medic.getPrenume(), i, 1);
            table.setValueAt(specializare.getDenumire(), i, 2);
            table.setValueAt(fisaPacient.getDiagnostic(), i, 3);
            table.setValueAt(fisaPacient.getTratament(), i, 4);
        }
    }
}

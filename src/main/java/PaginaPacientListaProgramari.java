import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class PaginaPacientListaProgramari
{
    protected static void afisareListaProgramari(JPanel panelBot, Pacient pacient)
    {
        List<Programare> listaProgramari;
        listaProgramari = PacientRepository.getListaProgramariInCurs(pacient);

        String columnNames[] = { "Index", "Data", "Ora", "Medic", "Departament" };
        int columnSizes[] = { 40, 130, 130, 160, 300 };
        int totalColumns = columnNames.length;

        JTable table = new JTable(listaProgramari.size(), totalColumns);

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

        for (int i = 0; i < listaProgramari.size(); i++)
        {
            Programare programare = listaProgramari.get(i);
            Medic medic = programare.getMedic();
            Specializare specializare = medic.getSpecializare();

            long time = programare.getData().getTime();
            SimpleDateFormat dateFormat;

            dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String data = dateFormat.format(new Date(time));

            dateFormat = new SimpleDateFormat("HH:mm");
            String ora = dateFormat.format(new Date(time));

            table.setValueAt(programare.getIndex(), i, 0);
            table.setValueAt(data, i, 1);
            table.setValueAt(ora, i , 2);
            table.setValueAt(medic.getNume() + " " + medic.getPrenume(), i, 3);
            table.setValueAt(specializare.getDenumire(), i, 4);
        }
    }
}

package Auth;

import Entities.Medic;
import Entities.Pacient;
import Entities.User;
import Repository.UserRepository;
import GUI.PaginaMedic;
import GUI.PaginaPacient;

public class Login
{
    public static boolean TryLogin(User user)
    {
        user = UserRepository.userLogin(user);

        if (user == null)
        {
            return false;
        }

        switch (user.getAccess())
        {
            case User.constUserAccessMedic:
                Medic medic = UserRepository.getMedic(user);

                if (medic == null)
                {
                    return false;
                }

                PaginaMedic.afisarePaginaMedic(medic);
                return true;

            case User.constUserAccessPacient:
                Pacient pacient = UserRepository.getPacient(user);

                if (pacient == null)
                {
                    return false;
                }

                PaginaPacient.afisarePaginaPacient(pacient);
                return true;
        }
        return false;
    }
}

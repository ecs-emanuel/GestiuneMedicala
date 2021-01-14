public class Signup
{
    public static final int constResultSuccess = 1;
    public static final int constResultFailExists = -1;
    public static final int constResultFailIncomplete = -2;
    public static final int constResultFailGeneral = -3;

    public static int TrySignUp(User user, Medic medic, Pacient pacient)
    {
        // Username not available
        if(!UserRepository.isValidUsername(user))
        {
            return constResultFailExists;
        }

        // Did not complete all fields required
        if(!UserRepository.isInformationComplete(user) || !(PacientRepository.isInformationComplete(pacient) || MedicRepository.isInformationComplete(medic)))
        {
            return constResultFailIncomplete;
        }

        int userIndex = UserRepository.addUser(user);

        // User information stored to SQL succesfully
        if(userIndex != UserRepository.constUserAddFail)
        {
            user.setIndex(userIndex);

            int result = UserRepository.constUserAddFail;

            // Account type
            switch(user.getAccess())
            {
                case User.constUserAccessMedic:
                    medic.setUser(user);
                    result = MedicRepository.addMedic(medic);
                    break;

                case User.constUserAccessPacient:
                    pacient.setUser(user);
                    result = PacientRepository.addPacient(pacient);
                    break;
            }

            // Couldn't store pacient/medic information to SQL for some reason, delete user information from SQL
            if(result == UserRepository.constUserAddFail || result == MedicRepository.constMedicAddFail || result == PacientRepository.constPacientAddFail)
            {
                UserRepository.removeUser(user);
                return constResultFailGeneral;
            }
        }
        return constResultSuccess;
    }
}

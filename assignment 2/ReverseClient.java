import ReverseModule.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

import java.io.*;

class ReverseClient {

    public static void main(String[] args) {

        Reverse Reverseimpl = null;

        try {

            // Initialize ORB
            ORB orb = ORB.init(args, null);

            // Get naming service reference
            org.omg.CORBA.Object objRef =
                    orb.resolve_initial_references("NameService");

            NamingContextExt ncRef =
                    NamingContextExtHelper.narrow(objRef);

            // Resolve object reference
            String name = "Reverse";

            Reverseimpl =
                    ReverseHelper.narrow(ncRef.resolve_str(name));

            // Input from user
            System.out.println("Enter String: ");

            BufferedReader br =
                    new BufferedReader(
                            new InputStreamReader(System.in));

            String str = br.readLine();

            // Call remote method
            String tempStr =
                    Reverseimpl.reverse_string(str);

            // Display result
            System.out.println(tempStr);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

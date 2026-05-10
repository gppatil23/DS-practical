import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;

import ReverseModule.Reverse;

class ReverseServer {

    public static void main(String[] args) {

        try {

            // Initialize ORB
            ORB orb = ORB.init(args, null);

            // Get reference to RootPOA
            POA rootPOA = POAHelper.narrow(
                    orb.resolve_initial_references("RootPOA"));

            // Activate POA manager
            rootPOA.the_POAManager().activate();

            // Create servant
            Reverseimpl rvr = new Reverseimpl();

            // Convert servant to CORBA reference
            org.omg.CORBA.Object ref =
                    rootPOA.servant_to_reference(rvr);

            System.out.println("Step 1");

            // Narrow reference
            Reverse h_ref =
                    ReverseModule.ReverseHelper.narrow(ref);

            System.out.println("Step 2");

            // Get naming context
            org.omg.CORBA.Object objRef =
                    orb.resolve_initial_references("NameService");

            System.out.println("Step 3");

            NamingContextExt ncRef =
                    NamingContextExtHelper.narrow(objRef);

            System.out.println("Step 4");

            // Bind object
            String name = "Reverse";

            NameComponent path[] = ncRef.to_name(name);

            ncRef.rebind(path, h_ref);

            System.out.println("Reverse Server Ready and Waiting...");

            // Wait for client requests
            orb.run();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package yassine;



import edu.devapps.entity.Centre;
import edu.devapps.entity.Reclamation;
import edu.devapps.entity.Reponse_rec;
import edu.devapps.entity.Service;


public interface MyListener {
  
    public void onClickListener(Reclamation Reclamation);

    public void onClickListener(Reponse_rec Reponse_rec);
        public void onClickListener(Centre centre);
                public void onClickListener(Service Service);


    
}

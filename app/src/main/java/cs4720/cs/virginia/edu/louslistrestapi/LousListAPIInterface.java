package cs4720.cs.virginia.edu.louslistrestapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by sherriff on 10/25/16.
 */

public interface LousListAPIInterface {

    @GET("{dept}?json")
    Call<List<Section>> sectionList(@Path("dept") String dept);

}

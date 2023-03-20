package com.course.graphql.component.fake;

import com.course.graphql.datasource.fake.FakeHelloDataSource;
import com.course.graphql.generated.DgsConstants;
import com.course.graphql.generated.types.Hello;
import com.course.graphql.generated.types.HelloInput;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;

@DgsComponent
public class FakeHelloMutation {

    @DgsMutation
    public int addHello(@InputArgument(name = "helloInput")HelloInput helloInput){
        var hello = Hello.newBuilder().text(helloInput.getText()).randomNuber(helloInput.getNumber()).build();
        FakeHelloDataSource.HELLO_LIST.add(hello);
        return FakeHelloDataSource.HELLO_LIST.size();
    }
}

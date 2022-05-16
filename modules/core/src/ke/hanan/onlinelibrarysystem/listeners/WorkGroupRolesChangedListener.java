//package ke.hanan.onlinelibrarysystem.listeners;
//
//import com.haulmont.cuba.core.app.events.EntityChangedEvent;
//import com.haulmont.cuba.core.global.DataManager;
//import com.haulmont.cuba.core.listener.AfterDeleteEntityListener;
//import com.haulmont.cuba.core.listener.AfterUpdateEntityListener;
//import ke.hanan.onlinelibrarysystem.entity.WorkGroupRoles;
//import org.slf4j.Logger;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.event.TransactionPhase;
//import org.springframework.transaction.event.TransactionalEventListener;
//import org.springframework.transaction.support.TransactionSynchronizationAdapter;
//import org.springframework.transaction.support.TransactionSynchronizationManager;
//
//import javax.inject.Inject;
//import java.sql.Connection;
//import java.util.UUID;
//
//@Component("onlinelibrarysystem_WorkGroupRolesChangedListener")
//public class WorkGroupRolesChangedListener  implements AfterUpdateEntityListener<WorkGroupRoles>, AfterDeleteEntityListener<WorkGroupRoles> {
////    @Inject
////    private UpdateWorkGroupsService updateWorkGroupsService;
//    @Inject
//    private Logger log;
//    @Inject
//    private DataManager dataManager;
//    @Override
//    public void onAfterUpdate(WorkGroupRoles entity, Connection connection) {
//        printUpdate(entity);
//
//    }
//    private void printUpdate(WorkGroupRoles workGroupRoles) {
//        log.info("In transaction work group role update: " + workGroupRoles);
//
//        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
//            @Override
//            public void afterCommit() {
//
//                updateWorkGroupsService.RefreshUpdateWorkGroups(workGroupRoles.getWorkGroup());
//                updateWorkGroupsService.RefreshPoliceUpdateWorkGroups(workGroupRoles.getWorkGroup());
//                log.info("work group role update");
//            }
//        });
//    }
////    @Override
////    public void onAfterDelete(WorkGroupRoles entity, Connection connection) {
////        WorkGroupRoleDelete(entity);
////
////    }
////    private void WorkGroupRoleDelete(WorkGroupRoles wG) {
////        log.info(" work group role deletion: " + wG);
////
////        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
////            @Override
////            public void afterCommit() {
////                updateWorkGroupsService.RefreshUpdateWorkGroups(wG.getWorkGroup());
////                updateWorkGroupsService.RefreshPoliceUpdateWorkGroups(wG.getWorkGroup());
//////                System.out.println("After transaction commit: " + customer);
////                log.info("workGroup role deletion");
////            }
////        });
////    }
////
//
//}
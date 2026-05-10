#include<mpi.h>
#include<stdio.h>
int main(int argc , char *argv[]){
int rank , size;
int arr[8]={1,2,3,4,5,6,7,8};
int local_sum = 0;
int global_sum;
MPI_Init(&argc,&argv);
MPI_Comm_rank(MPI_COMM_WORLD , &rank);
MPI_Comm_size(MPI_COMM_WORLD , &size);
for(int i=rank; i<8; i+=size){
  local_sum += arr[i];
}
printf("Process %d calculated partial sum= %d\n" , rank , local_sum);
MPI_Reduce(&local_sum, &global_sum, 1, MPI_INT, MPI_SUM, 0,MPI_COMM_WORLD);
if(rank == 0){
printf("Final sum= %d\n", global_sum);
}
MPI_Finalize();
return 0;
}

/*
 * Title:        CloudSim Toolkit
 * Description:  CloudSim (Cloud Simulation) Toolkit for Modeling and Simulation
 *               of Clouds
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2009, The University of Melbourne, Australia
 */

package org.cloudbus.cloudsim.examples;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.lang.*;


import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.CloudletSchedulerTimeShared;
import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.DatacenterBroker;
import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.Storage;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.VmAllocationPolicySimple;
import org.cloudbus.cloudsim.VmSchedulerTimeShared;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;




/**
 * A simple example showing how to create
 * a datacenter with two hosts and run two
 * cloudlets on it. The cloudlets run in
 * VMs with different MIPS requirements.
 * The cloudlets will take different time
 * to complete the execution depending on
 * the requested VM performance.
 */
public class CloudSimExample3 {

	/** The  list. */
	

	/**
	 * Creates main() to run this example
	 */
	public static void main(String[] args) {

		Log.printLine("Starting CloudSimExample3...");

		try {
			// First step: Initialize the CloudSim package. It should be called
			// before creating any entities.
			int num_user = 1;   // number of cloud users
			Calendar calendar = Calendar.getInstance();
			boolean trace_flag = false;  // mean trace events

			// Initialize the CloudSim library
			CloudSim.init(num_user, calendar, trace_flag);

			// Second step: Create 
			// are the resource providers in CloudSim. We need at list one of them to run a CloudSim simulation
			@SuppressWarnings("unused")
			Datacenter datacenter0 = createDatacenter("Datacenter_0");

			//Third step: Create Broker
			DatacenterBroker broker = createBroker();
			int brokerId = broker.getId();

			//Fourth step: Create one virtual machine
			ArrayList<vm3> vmlist = new ArrayList<vm3>();

			//VM description
			int vmid = 0;
			int mips = 250;
			long size = 10000; //image size (MB)
			int ram = 2048; //vm memory (MB)
			long bw = 1000;
			int pesNumber = 1; //number of cpus
			String vmm = "Xen"; //VMM name
			double[] executioncost1= {1.23,1.12,1.15};

			//create two VMs
			int[] datasize1= {30,30};
			vm3 Vm1 = new vm3(vmid, brokerId, mips, pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared(), executioncost1, datasize1);

			//the second VM will have twice the priority of VM1 and so will receive twice CPU time
			vmid++;
			double[] executioncost2= {1.17,1.17,1.28};
			int[] datasize2= {10,10};
			vm3 Vm2 = new vm3(vmid, brokerId, mips , pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared(), executioncost2, datasize2);
			vmid++;
			double[] executioncost3= {1.13,1.11,1.11};
			int[] datasize3= {10,10};
			vm3 Vm3 = new vm3(vmid, brokerId, mips, pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared(), executioncost3, datasize3);
			vmid++;
			double[] executioncost4= {1.26,1.12,1.14};
			int[] datasize4= {10,10};
			vm3 Vm4 = new vm3(vmid, brokerId, mips , pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared(), executioncost4, datasize4);
			vmid++;
			double[] executioncost5= {1.19,1.14,1.22};
			int[] datasize5= {10,10};
			vm3 Vm5 = new vm3(vmid, brokerId, mips , pesNumber, ram, bw, size, vmm, new CloudletSchedulerTimeShared(), executioncost5, datasize5);


			//add the VMs to the vmList
			vmlist.add(Vm1);
			vmlist.add(Vm2);
			vmlist.add(Vm3);
			vmlist.add(Vm4);
			vmlist.add(Vm5);
			
			//submit vm list to the broker
			broker.submitVmList(vmlist);


			//Fifth step: Create two 
			

			// properties
			int id = 0;
			long length = 40000;
			long fileSize = 300;
			long outputSize = 300;
			UtilizationModel utilizationModel = new UtilizationModelFull();
			
            double[] comcost1= {0.00,0.17,0.21};
            
			cloudlet3 Cloudlet1 = new cloudlet3(id, length, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel, comcost1);
			Cloudlet1.setUserId(brokerId);

			id++;
			double[] comcost2= {0.17,0.00,0.22};
			
			cloudlet3 Cloudlet2 = new cloudlet3(id, length, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel, comcost2);
			Cloudlet2.setUserId(brokerId);

			id++;
			double[] comcost3= {0.21,0.22,0.00};
			
			cloudlet3 Cloudlet3 = new cloudlet3(id, length, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel, comcost3);
			Cloudlet3.setUserId(brokerId);
			List<cloudlet3> cloudletList=new ArrayList<cloudlet3>();

			/** The . */
			

			


			//add the  to the list
			cloudletList.add(Cloudlet1);
			cloudletList.add(Cloudlet2);
			cloudletList.add(Cloudlet3);

			//submit  list to the broker
			broker.submitCloudletList(cloudletList);
			GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.001, 0.95, 2);

			// Initialize population
			Population population = ga.initPopulation(5);

			// Evaluate population
			ga.evalPopulation(population);

			// Keep track of current generation
			

			/**
			 * Start the evolution loop
			 * 
			 * Every genetic algorithm problem has different criteria for finishing.
			 * In this case, we know what a perfect solution looks like (we don't
			 * always!), so our isTerminationConditionMet method is very
			 * straightforward: if there's a member of the population whose
			 * chromosome is all ones, we're done!
			 */
			int i=1;
			while (i<= 500) {
				// Print fittest individual from population
				System.out.println("Best solution: " + population.getFittest(0).toString());

				// Apply crossover
				population = ga.crossoverPopulation(population);

				// Apply mutation
				population = ga.mutatePopulation(population);

				// Evaluate population
				ga.evalPopulation(population);

				// Increment the current generation
				i++;
				
			}

			/**
			 * We're out of the loop now, which means we have a perfect solution on
			 * our hands. Let's print it out to confirm that it is actually all
			 * ones, as promised.
			 */
			
			System.out.println("Best solution: " + population.getFittest(0).toString());


			//bind the  to the vms. This way, the broker
			// will submit the bound  only to the specific VM
			

			// Sixth step: Starts the simulation
			CloudSim.startSimulation();


			// Final step: Print results when simulation is over
			List<Cloudlet> newList = broker.getCloudletReceivedList();

			CloudSim.stopSimulation();

        	printCloudletList(newList);

			Log.printLine("CloudSimExample3 finished!");
		}
		catch (Exception e) {
			e.printStackTrace();
			Log.printLine("The simulation has been terminated due to an unexpected error");
		}
	}

	private static int pow(int i, int j) {
		// TODO Auto-generated method stub
		return 0;
	}

	private static Datacenter createDatacenter(String name){

		// Here are the steps needed to create a PowerDatacenter:
		// 1. We need to create a list to store
		//    our machine
		List<Host> hostList = new ArrayList<Host>();

		// 2. A Machine contains one or more PEs or CPUs/Cores.
		// In this example, it will have only one core.
		List<Pe> peList = new ArrayList<Pe>();

		int mips = 1000;

		// 3. Create PEs and add these into a list.
		peList.add(new Pe(0, new PeProvisionerSimple(mips))); // need to store Pe id and MIPS Rating

		//4. Create Hosts with its id and list of PEs and add them to the list of machines
		int hostId=0;
		int ram = 2048; //host memory (MB)
		long storage = 1000000; //host storage
		int bw = 10000;

		hostList.add(
    			new Host(
    				hostId,
    				new RamProvisionerSimple(ram),
    				new BwProvisionerSimple(bw),
    				storage,
    				peList,
    				new VmSchedulerTimeShared(peList)
    			)
    		); // This is our first machine

		//create another machine in the Data center
		List<Pe> peList2 = new ArrayList<Pe>();

		peList2.add(new Pe(0, new PeProvisionerSimple(mips)));

		hostId++;

		hostList.add(
    			new Host(
    				hostId,
    				new RamProvisionerSimple(ram),
    				new BwProvisionerSimple(bw),
    				storage,
    				peList2,
    				new VmSchedulerTimeShared(peList2)
    			)
    		); // This is our second machine



		// 5. Create a DatacenterCharacteristics object that stores the
		//    properties of a data center: architecture, OS, list of
		//    Machines, allocation policy: time- or space-shared, time zone
		//    and its price (G$/Pe time unit).
		String arch = "x64";      // system architecture
		String os = "Windows";          // operating system
		String vmm = "Xen";
		double time_zone = 10.0;         // time zone this resource located
		double cost = 3.0;              // the cost of using processing in this resource
		double costPerMem = 0.05;		// the cost of using memory in this resource
		double costPerStorage = 0.001;	// the cost of using storage in this resource
		double costPerBw = 0.0;			// the cost of using bw in this resource
		LinkedList<Storage> storageList = new LinkedList<Storage>();	//we are not adding SAN devices by now

        DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
                arch, os, vmm, hostList, time_zone, cost, costPerMem, costPerStorage, costPerBw);

		// 6. Finally, we need to create a PowerDatacenter object.
		Datacenter datacenter = null;
		try {
			datacenter = new Datacenter(name, characteristics, new VmAllocationPolicySimple(hostList), storageList, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return datacenter;
	}

	//We strongly encourage users to develop their own broker policies, to submit vms and  according
	//to the specific rules of the simulated scenario
	private static DatacenterBroker createBroker(){

		DatacenterBroker broker = null;
		try {
			broker = new DatacenterBroker("Broker");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return broker;
	}

	/**
	 * Prints the  objects
	 * @param list  list of Cloudlets
	 */
	private static void printCloudletList(List<Cloudlet> list) {
		int size = list.size();
		Cloudlet cloudlet;

		String indent = "    ";
		Log.printLine();
		Log.printLine("========== OUTPUT ==========");
		Log.printLine("Cloudlet ID" + indent + "STATUS" + indent +
				"Data center ID" + indent + "VM ID" + indent + "Time" + indent + "Start Time" + indent + "Finish Time");

		DecimalFormat dft = new DecimalFormat("###.##");
		for (int i = 0; i < size; i++) {
			cloudlet = list.get(i);
			Log.print(indent + cloudlet.getCloudletId() + indent + indent);

			if (cloudlet.getCloudletStatus() == Cloudlet.SUCCESS){
				Log.print("SUCCESS");

				Log.printLine( indent + indent + cloudlet.getResourceId() + indent + indent + indent + cloudlet.getVmId() +
						indent + indent + dft.format(cloudlet.getActualCPUTime()) + indent + indent + dft.format(cloudlet.getExecStartTime())+
						indent + indent + dft.format(cloudlet.getFinishTime()));
			}
		}

	}
}

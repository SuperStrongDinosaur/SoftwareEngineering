# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.12

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/local/Cellar/cmake/3.12.3/bin/cmake

# The command to remove a file.
RM = /usr/local/Cellar/cmake/3.12.3/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /Users/SuperStrongDinosaur/Programing/SoftwareEngineering/LRUCache/LRUCache/test

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /Users/SuperStrongDinosaur/Programing/SoftwareEngineering/LRUCache/LRUCache/test

# Include any dependencies generated for this target.
include gtestLib/CMakeFiles/gtest_main.dir/depend.make

# Include the progress variables for this target.
include gtestLib/CMakeFiles/gtest_main.dir/progress.make

# Include the compile flags for this target's objects.
include gtestLib/CMakeFiles/gtest_main.dir/flags.make

gtestLib/CMakeFiles/gtest_main.dir/src/gtest_main.o: gtestLib/CMakeFiles/gtest_main.dir/flags.make
gtestLib/CMakeFiles/gtest_main.dir/src/gtest_main.o: gtestLib/src/gtest_main.cc
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/SuperStrongDinosaur/Programing/SoftwareEngineering/LRUCache/LRUCache/test/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object gtestLib/CMakeFiles/gtest_main.dir/src/gtest_main.o"
	cd /Users/SuperStrongDinosaur/Programing/SoftwareEngineering/LRUCache/LRUCache/test/gtestLib && /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/gtest_main.dir/src/gtest_main.o -c /Users/SuperStrongDinosaur/Programing/SoftwareEngineering/LRUCache/LRUCache/test/gtestLib/src/gtest_main.cc

gtestLib/CMakeFiles/gtest_main.dir/src/gtest_main.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/gtest_main.dir/src/gtest_main.i"
	cd /Users/SuperStrongDinosaur/Programing/SoftwareEngineering/LRUCache/LRUCache/test/gtestLib && /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Users/SuperStrongDinosaur/Programing/SoftwareEngineering/LRUCache/LRUCache/test/gtestLib/src/gtest_main.cc > CMakeFiles/gtest_main.dir/src/gtest_main.i

gtestLib/CMakeFiles/gtest_main.dir/src/gtest_main.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/gtest_main.dir/src/gtest_main.s"
	cd /Users/SuperStrongDinosaur/Programing/SoftwareEngineering/LRUCache/LRUCache/test/gtestLib && /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Users/SuperStrongDinosaur/Programing/SoftwareEngineering/LRUCache/LRUCache/test/gtestLib/src/gtest_main.cc -o CMakeFiles/gtest_main.dir/src/gtest_main.s

# Object files for target gtest_main
gtest_main_OBJECTS = \
"CMakeFiles/gtest_main.dir/src/gtest_main.o"

# External object files for target gtest_main
gtest_main_EXTERNAL_OBJECTS =

gtestLib/libgtest_main.a: gtestLib/CMakeFiles/gtest_main.dir/src/gtest_main.o
gtestLib/libgtest_main.a: gtestLib/CMakeFiles/gtest_main.dir/build.make
gtestLib/libgtest_main.a: gtestLib/CMakeFiles/gtest_main.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/Users/SuperStrongDinosaur/Programing/SoftwareEngineering/LRUCache/LRUCache/test/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX static library libgtest_main.a"
	cd /Users/SuperStrongDinosaur/Programing/SoftwareEngineering/LRUCache/LRUCache/test/gtestLib && $(CMAKE_COMMAND) -P CMakeFiles/gtest_main.dir/cmake_clean_target.cmake
	cd /Users/SuperStrongDinosaur/Programing/SoftwareEngineering/LRUCache/LRUCache/test/gtestLib && $(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/gtest_main.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
gtestLib/CMakeFiles/gtest_main.dir/build: gtestLib/libgtest_main.a

.PHONY : gtestLib/CMakeFiles/gtest_main.dir/build

gtestLib/CMakeFiles/gtest_main.dir/clean:
	cd /Users/SuperStrongDinosaur/Programing/SoftwareEngineering/LRUCache/LRUCache/test/gtestLib && $(CMAKE_COMMAND) -P CMakeFiles/gtest_main.dir/cmake_clean.cmake
.PHONY : gtestLib/CMakeFiles/gtest_main.dir/clean

gtestLib/CMakeFiles/gtest_main.dir/depend:
	cd /Users/SuperStrongDinosaur/Programing/SoftwareEngineering/LRUCache/LRUCache/test && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/SuperStrongDinosaur/Programing/SoftwareEngineering/LRUCache/LRUCache/test /Users/SuperStrongDinosaur/Programing/SoftwareEngineering/LRUCache/LRUCache/test/gtestLib /Users/SuperStrongDinosaur/Programing/SoftwareEngineering/LRUCache/LRUCache/test /Users/SuperStrongDinosaur/Programing/SoftwareEngineering/LRUCache/LRUCache/test/gtestLib /Users/SuperStrongDinosaur/Programing/SoftwareEngineering/LRUCache/LRUCache/test/gtestLib/CMakeFiles/gtest_main.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : gtestLib/CMakeFiles/gtest_main.dir/depend


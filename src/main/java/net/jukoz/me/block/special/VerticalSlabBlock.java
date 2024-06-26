package net.jukoz.me.block.special;

import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.block.enums.StairShape;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class VerticalSlabBlock extends Block implements Waterloggable {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final BooleanProperty DOUBLE = BooleanProperty.of("double");

    public VerticalSlabBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false).with(DOUBLE, false));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED, DOUBLE);
    }

    @Override
    public boolean hasSidedTransparency(BlockState state) {
        return !state.get(DOUBLE);
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        BlockState blockState = ctx.getWorld().getBlockState(blockPos);
        if (blockState.isOf(this)) {
            return (blockState.with(DOUBLE, true)).with(WATERLOGGED, false);
        } else {
            return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())
                    .with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER)
                    .with(DOUBLE, false);
        }
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        ItemStack itemStack = context.getStack();
        if (state.get(DOUBLE) || !itemStack.isOf(this.asItem())) {
            return false;
        }
        if (context.canReplaceExisting()) {
            return context.getSide() == state.get(FACING);
        }
        return true;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if(state.get(DOUBLE)) return VoxelShapes.cuboid(0, 0, 0.0, 1, 1, 1);
        return switch(state.get(Properties.HORIZONTAL_FACING)) {
            case WEST -> VoxelShapes.cuboid(0.5, 0, 0, 1, 1, 1);
            case EAST -> VoxelShapes.cuboid(0, 0, 0, 0.5, 1, 1);
            case SOUTH -> VoxelShapes.cuboid(0, 0, 0, 1, 1, 0.5);
            case NORTH -> VoxelShapes.cuboid(0, 0, 0.5, 1, 1, 1);
            default -> VoxelShapes.cuboid(1,1,1,1,1,1);
        };
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        Direction direction = state.get(FACING);
        switch (mirror) {
            case LEFT_RIGHT -> {
                if (direction.getAxis() != Direction.Axis.Z) break;
                return state.rotate(BlockRotation.CLOCKWISE_180);
            }
            case FRONT_BACK -> {
                if (direction.getAxis() != Direction.Axis.X) break;
                return state.rotate(BlockRotation.CLOCKWISE_180);
            }
        }
        return super.mirror(state, mirror);
    }
}
